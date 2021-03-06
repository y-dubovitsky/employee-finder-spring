package ru.ydubovitsky.employeefinder.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.ydubovitsky.employeefinder.dto.WorkDto;
import ru.ydubovitsky.employeefinder.entity.User;
import ru.ydubovitsky.employeefinder.entity.Work;
import ru.ydubovitsky.employeefinder.repository.UserRepository;
import ru.ydubovitsky.employeefinder.repository.WorkRepository;

import java.security.Principal;

@Service
public class WorkService {

    private final Logger LOGGER = LoggerFactory.getLogger(WorkService.class);

    private WorkRepository workRepository;
    private UserRepository userRepository;

    public WorkService(WorkRepository workRepository, UserRepository userRepository) {
        this.workRepository = workRepository;
        this.userRepository = userRepository;
    }

    public Work addUserWork(WorkDto workDto, Principal principal) {
        User user = userRepository.findByUsername(principal.getName());

        Work work = new Work();
        work.setName(workDto.getName());
        work.setAchievements(workDto.getPosition());
        work.setPosition(workDto.getPosition());
        work.setFunction(workDto.getFunction());
        work.setStartDate(workDto.getStartDate());
        work.setEndDate(workDto.getEndDate());
        work.setUser(user);

        Work savedWork = workRepository.save(work);
        LOGGER.info(work.getName() + " saved!");
        user.getWorks().add(work);

        userRepository.save(user);
        LOGGER.info(user.getUsername() + " updated!");

        return savedWork;
    }
}
