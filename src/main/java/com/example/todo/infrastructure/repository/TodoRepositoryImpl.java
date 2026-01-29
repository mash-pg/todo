package com.example.todo.infrastructure.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.example.todo.domain.entity.Todo;
import com.example.todo.domain.repository.TodoRepository;
import com.example.todo.domain.valueobject.TodoId;
import com.example.todo.domain.valueobject.TodoTitle;
import com.example.todo.infrastructure.jpa.TodoJpaEntity;

/**
 * TodoRepository の実装クラス（橋渡し役・翻訳者）
 *
 * 役割:
 * - Domain層 と Infrastructure層 をつなぐ
 * - Domain Entity ↔ JPA Entity の変換を担当
 *
 * ポイント:
 * - implements TodoRepository  → Domain層のインターフェースを実装
 * - 使う: TodoJpaRepository    → 実際のDB操作はこっちに委譲
 *
 * 処理の流れ（saveの例）:
 * 1. Domain Entity（Todo）を受け取る
 * 2. JPA Entity（TodoJpaEntity）に変換する
 * 3. JpaRepository でDB操作する
 * 4. 結果を Domain Entity に変換して返す
 */
@Repository  // Springに「これはRepositoryだよ」と伝える（DIコンテナに登録）
public class TodoRepositoryImpl implements TodoRepository {
	// ↑ TodoRepository（Domain層のinterface）を実装する
	// ↑ TodoJpaRepository ではない！（最初ここを間違えた）

	// 実際のDB操作を行うSpring Data JPAのRepository
	private final TodoJpaRepository jpaRepository;

	// コンストラクタインジェクション（SpringがTodoJpaRepositoryを自動で渡してくれる）
	public TodoRepositoryImpl(TodoJpaRepository jpaRepository) {
		this.jpaRepository = jpaRepository;
	}

	// ===== インターフェースの実装 =====

	@Override
	public Todo save(Todo todo) {
		// ① Domain Entity → JPA Entity に変換
		TodoJpaEntity jpaEntity = toJpaEntity(todo);
		// ② JpaRepository で保存（DBにINSERT/UPDATE）
		TodoJpaEntity savedEntity = jpaRepository.save(jpaEntity);
		// ③ JPA Entity → Domain Entity に変換して返す
		return toDomainEntity(savedEntity);
	}

	@Override
	public Optional<Todo> findById(TodoId id) {
		// JpaRepository で検索し、見つかったら Domain Entity に変換
		// map(this::toDomainEntity) は「中身があれば変換する」という意味
		return jpaRepository.findById(id.getValue()).map(this::toDomainEntity);
	}

	@Override
	public List<Todo> findAll() {
		// 全件取得して、各要素を Domain Entity に変換
		return jpaRepository.findAll().stream()
				.map(this::toDomainEntity)
				.collect(Collectors.toList());
	}

	@Override
	public void deleteById(TodoId id) {
		jpaRepository.deleteById(id.getValue());
	}

	// ===== 変換メソッド（private：このクラス内でだけ使う） =====

	/**
	 * Domain Entity → JPA Entity 変換（保存する前に呼ぶ）
	 *
	 * Value Object から中身を取り出す:
	 * - TodoId → Long（getId()）
	 * - TodoTitle → String（getValue()）
	 */
	private TodoJpaEntity toJpaEntity(Todo todo) {
		TodoJpaEntity entity = new TodoJpaEntity();
		// IDがある場合だけセット（新規作成時はnull）
		if (todo.getId() != null) {
			entity.setId(todo.getId().getValue());
		}
		entity.setTitle(todo.getTitle().getValue());
		entity.setStatus(todo.getStatus());
		entity.setPriority(todo.getPriority());
		entity.setDescription(todo.getDescription());
		entity.setDueDate(todo.getDueDate());
		entity.setCreated_At(todo.getCreatedAt());
		entity.setUpdated_At(todo.getUpdatedAt());
		return entity;
	}

	/**
	 * JPA Entity → Domain Entity 変換（取得した後に呼ぶ）
	 *
	 * シンプルな型を Value Object で包む:
	 * - Long → TodoId
	 * - String → TodoTitle
	 */
	private Todo toDomainEntity(TodoJpaEntity jpaEntity) {
		return new Todo(
				new TodoId(jpaEntity.getId()),
				new TodoTitle(jpaEntity.getTitle()),
				jpaEntity.getStatus(),
				jpaEntity.getPriority(),
				jpaEntity.getDescription(),
				jpaEntity.getDueDate(),
				jpaEntity.getCreatedAt(),
				jpaEntity.getUpdatedAt()
		);
	}
}
