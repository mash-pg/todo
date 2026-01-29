package com.example.todo.infrastructure.jpa;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.todo.domain.valueobject.TodoPriority;
import com.example.todo.domain.valueobject.TodoStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * JPA用Entity（DBテーブルと1:1で対応する箱）
 *
 * なぜ Domain Entity と分けるか？
 * - Domain Entity に @Entity を付けると JPA（DB技術）に依存してしまう
 * - ビジネスルールとDBの都合を分離したい
 * - DBを変えても Domain層 は変更不要にする
 */
@Entity  // 「これはDBテーブルに対応するクラスだよ」とJPAに伝える
@Table(name = "todos")  // テーブル名を指定
public class TodoJpaEntity {

	@Id  // 主キー
	@GeneratedValue(strategy = GenerationType.IDENTITY)  // DBが自動でIDを振る
	private Long id;

	@Column(nullable = false, length = 100)  // NOT NULL、最大100文字
	private String title;

	@Column(nullable = false, length = 20)
	@Enumerated(EnumType.STRING)  // Enumを文字列("OPEN","DONE")で保存。これがないと0,1の数字になる
	private TodoStatus status;
	
	@Column(nullable = false, length = 20)
	@Enumerated(EnumType.STRING)  // Enumを文字列("HIGH","MEDIUM","LOW")で保存。
	private TodoPriority priority;
	
	@Column(nullable = true)  
	private String description;// IS NULL、文字数無制限
	
	@Column(nullable = true)
	private LocalDate duedate;
	
	@Column(nullable = true)
	private LocalDateTime created_at;
	
	@Column(nullable = true)
	private LocalDateTime updated_at;
	

	// 引数なしコンストラクタ（JPA必須：JPAがリフレクションでインスタンスを作るため）
	public TodoJpaEntity() {}

	// 全引数コンストラクタ
	public TodoJpaEntity(Long id, String title, TodoStatus status,
			TodoPriority priority,String description,LocalDate duedate,
			LocalDateTime created_at,LocalDateTime updated_at) {
		this.id = id;
		this.title = title;
		this.status = status;
		this.priority = priority;
		this.description = description;
		this.duedate = duedate;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}

	//Getter
	public Long getId() {
		return this.id;
	}
	public String getTitle() {
		return this.title;
	}
	public TodoStatus getStatus() {
		return this.status;
	}
	public TodoPriority getPriority() {
		return this.priority;
	}
	public String getDescription() {
		return this.description;
	}
	public LocalDate getDueDate() {
		return this.duedate;
	}
	public LocalDateTime getCreatedAt() {
		return this.created_at;
	}
	
	public LocalDateTime getUpdatedAt() {
		return this.updated_at;
	}
	
	//Setter
	public void setId(Long id) {
		this.id = id;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setStatus(TodoStatus status) {
		this.status = status;
	}
	public void setPriority(TodoPriority priority) {
		this.priority = priority;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setDueDate(LocalDate duedate) {
		this.duedate = duedate;
	}
	public void setCreated_At(LocalDateTime created_at) {
		this.created_at = created_at;
	}
	public void setUpdated_At(LocalDateTime updated_at) {
		this.updated_at = updated_at;
	}

}
