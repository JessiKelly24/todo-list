package br.com.todolist.todolist.controller;

import br.com.todolist.todolist.model.Task;
import br.com.todolist.todolist.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
@Slf4j
public class TaskController {

  // @Autowired
    private TaskService taskService;

    @Operation(summary = "Cria uma nova tarefa", description = "Retorna a tarefa criada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarefa criada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Houve um erro ao criar a tarefa, verifique as informações")})


    @PostMapping("/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@RequestBody Task task) {
        log.info("Criando uma nova tarefa com as informações [{}]", task);
        System.out.println(task);
        return taskService.createTask(task);
    }

    @Operation(summary = "Lista todas as tarefas", description = "Retorna a lista de tarefas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarefas listadas com sucesso"),
            @ApiResponse(responseCode = "500", description = "Houve um erro ao listar as tarefas")})

    @GetMapping("/tasks")
    @ResponseStatus(HttpStatus.OK)
    public List<Task> getAllTasks() {
        log.info("Listando todas as tarefas cadastradas");
        return taskService.listAllTasks();
    }

    @Operation(summary = "Buscando uma tarefa pelo id", description = "Retorna a lista de tarefas pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarefas encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não foi encontrado nenhuma tarefa com esse id")})

    @GetMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Task> getTasksById(@PathVariable(value = "id") Long id) {
        log.info("Buscando tarefa com o id [{}]", id);
        return taskService.findTaskById(id);
    }

    @Operation(summary = "Atualizando uma tarefa", description = "Retorna a lista de tarefas atualizada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarefas atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não foi possivel atualizar a tarefa - tarefa não encontrada")})

    @PutMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Task> updateTasksById(@PathVariable(value = "id") Long id, @RequestBody Task task) {
        log.info("Atualizando a tarefa com o id [{}] as novas informações são: [{}]", id, task);
        return taskService.updateTaskById(task, id);
    }

    @Operation(summary = "Excluindo uma terefa", description = "Retorna a lista de tarefas atualizada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Tarefa excluida com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não foi possivel excluir a tarefa - tarefa não encontrada")})

    @DeleteMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // pq usar content?
    public ResponseEntity<Object> deleteTaskById(@PathVariable(value = "id") Long id) {
        log.info("Excluindo tarefas com o id [{}]", id);
        return taskService.deleteById(id);
    }

}
