package com.example.todo.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.todo.infrastructure.jpa.TodoJpaEntity;

/**
 * Spring Data JPA のリポジトリ
 *
 * JpaRepository を継承するだけで CRUD メソッドが自動で使える（魔法のような機能）
 *
 * extends JpaRepository<TodoJpaEntity, Long>
 *                       ↑              ↑
 *                    操作対象       主キーの型
 *
 * 自動で使えるメソッド:
 * - save(entity)     : 保存（新規 or 更新）
 * - findById(id)     : ID検索 → Optional<TodoJpaEntity>
 * - findAll()        : 全件取得 → List<TodoJpaEntity>
 * - deleteById(id)   : 削除
 */
public interface TodoJpaRepository extends JpaRepository<TodoJpaEntity, Long> {
	// メソッドを書かなくても上記が使える！
}