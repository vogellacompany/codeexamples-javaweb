package com.vogella;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public interface ITodoService {
	
	void getTodos(Consumer<List<Todo>> todosConsumer);

	boolean saveTodo(ITodo todo);

	Optional<Todo> getTodo(long id);

	boolean deleteTodo(long id);

}