package com.example.dendiemsite.Controllers;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;
import com.example.dendiemsite.Entity.CommentEntity;
import com.example.dendiemsite.Entity.NewsEntity;
import com.example.dendiemsite.Entity.TaskEntity;
import com.example.dendiemsite.Entity.UserEntity;
import com.example.dendiemsite.Model.DateDTO;
import com.example.dendiemsite.Model.NewsDTO;
import com.example.dendiemsite.Model.TaskDTO;
import com.example.dendiemsite.Reprository.TaskRepo;
import com.example.dendiemsite.Reprository.UserRepo;
import com.example.dendiemsite.Service.CommentService;
import com.example.dendiemsite.Service.NewsService;
import com.example.dendiemsite.Service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.util.Comparator.comparingInt;

@RequiredArgsConstructor
@Controller
public class TaskController {
    @Autowired
    private ApplicationContext appContext;
    @Autowired
    private TaskRepo taskRepo;
    @Autowired
    private UserRepo userRepo;
    @PostMapping(value = "/add-task")
    public String addTask(
            @AuthenticationPrincipal UserEntity user,
            @ModelAttribute TaskDTO taskModel, Model model) {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String currD = dateFormat.format(date);
        model.addAttribute("taskModel",taskModel);
         System.out.println(taskModel.getTaskStartDate());
        TaskService taskService = appContext.getBean(TaskService.class);
        TaskEntity te = new TaskEntity("meet",taskModel.getTaskStartDate(),currD,user,"0","none");
        taskService.createTask(te);


        System.out.println("News added");


        return "redirect:news-list";
    }

    @GetMapping(value = "/task-no-active-list")
    public String all_no_active(@AuthenticationPrincipal UserEntity u, Model model) {
        TaskService taskService = appContext.getBean(TaskService.class);
        List<TaskEntity> te = taskService.findAllNews();
        te.removeIf(obj -> obj.getIsActive().equals("1"));

        model.addAttribute("tasks", te);
        return "all-no-active";
    }
    @GetMapping(value = "/task-active-list")
    public String all_active(@AuthenticationPrincipal UserEntity u, Model model) {
        TaskService taskService = appContext.getBean(TaskService.class);
        List<TaskEntity> te = taskService.findAllNews();
        te.removeIf(obj -> obj.getIsActive().equals("0"));

        List<TaskEntity> unique = new ArrayList<>();
        for (TaskEntity te1: te
             ) {
            if(unique.stream().anyMatch(o -> o.getAuthor().getId().equals(te1.getAuthor().getId()))){

            }else
            {
                unique.add(te1);
            }

        }

        model.addAttribute("tasks", unique);
        return "all_active";
    }
    @GetMapping("/task-admin/{id}")
    public String userEditForm(Model model, @PathVariable UserEntity id) throws ParseException {
        model.addAttribute("user",id);
        List<TaskEntity>  all_no = taskRepo.findAll();
        all_no.removeIf(obj -> obj.getIsActive().equals("1")&& obj.getAuthor().getId().equals(id.getId()));
        System.out.println(all_no);
        all_no.forEach(c -> c.setIsActive("1"));
        System.out.println(all_no);
        taskRepo.saveAll(all_no);

        System.out.println(id);

        List<TaskEntity>  all = taskRepo.findAll();
        all.removeIf(obj -> obj.getIsActive().equals("0")|| obj.getAuthor().getId()!=id.getId());
        System.out.println(all);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String currD = dateFormat.format(date);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        List<TaskEntity> taskEntities = new ArrayList<>();
        List<DateDTO> dateDTOS = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            for (TaskEntity taskEntity: all
            ) {


                if(!taskEntity.getTaskType().equals("meet")&&sdf.parse(currD) .equals( sdf.parse(taskEntity.getTaskEndDate()))){
                    taskEntities.add(taskEntity);
                }
            }
            String dayOfWeek = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(new SimpleDateFormat("yyyy-M-d").parse(currD));
            dateDTOS.add(new DateDTO(dayOfWeek,currD,new ArrayList<>(taskEntities)));

            Calendar c1 = Calendar.getInstance();
            c1.setTime(sdf.parse(currD));
            c1.add(Calendar.DATE, 1);  // number of days to add
            currD = sdf.format(c1.getTime());


            taskEntities.clear();

        }



        model.addAttribute("myUser",id);
        model.addAttribute("tasks",dateDTOS);
        return "tasks-admin-one-page";
    }
    @PostMapping(value = "/add-task-admin")
    public String addTaskAdmin(
            @AuthenticationPrincipal UserEntity user,
            @ModelAttribute TaskDTO taskModel, Model model) throws ParseException {


        model.addAttribute("taskModel",taskModel);
        Optional<UserEntity> muser = userRepo.findById(taskModel.getTaskUserId());

        TaskService newsService = appContext.getBean(TaskService.class);
        newsService.createTask(taskModel,muser.orElse(null));


        return "redirect:task-admin/"+taskModel.getTaskUserId();
    }
    @GetMapping(value = "/add_action")
    public String newsList(@AuthenticationPrincipal UserEntity u, Model model) {
        return "go-start";
    }
    @GetMapping("/my-tasks/{id}")
    public String myTasks(Model model, @PathVariable UserEntity id) throws ParseException {
        model.addAttribute("user",id);
        List<TaskEntity>  all_no = taskRepo.findAll();
        all_no.removeIf(obj -> obj.getIsActive().equals("0") || obj.getAuthor().getId()!=(id.getId()));


        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String currD = dateFormat.format(date);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        List<TaskEntity> taskEntities = new ArrayList<>();
        List<DateDTO> dateDTOS = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            for (TaskEntity taskEntity: all_no
            ) {

                System.out.println(sdf.parse(currD));
                System.out.println(sdf.parse(taskEntity.getTaskEndDate()));
                if(sdf.parse(currD) .equals( sdf.parse(taskEntity.getTaskEndDate()))){
                   taskEntities.add(taskEntity);
                }
            }
            String dayOfWeek = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(new SimpleDateFormat("yyyy-M-d").parse(currD));
            dateDTOS.add(new DateDTO(dayOfWeek,currD,new ArrayList<>(taskEntities)));
            System.out.println("sssssssssssssssssssssssssssssssssssssssss");
            System.out.println(currD);
            Calendar c1 = Calendar.getInstance();
            c1.setTime(sdf.parse(currD));
            c1.add(Calendar.DATE, 1);  // number of days to add
            currD = sdf.format(c1.getTime());

            System.out.println(taskEntities);
            taskEntities.clear();

        }

        model.addAttribute("myUser",id);
        model.addAttribute("tasks",dateDTOS);
        return "my-tasks";
    }
}
