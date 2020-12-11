package com.kukilej.springdataredisdemo.service;

import com.kukilej.springdataredisdemo.exception.ApplicationNotFoundException;
import com.kukilej.springdataredisdemo.model.Apply;
import com.kukilej.springdataredisdemo.repository.ApplyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class ApplyService {

    ApplyRepository applyRepository;

    public ApplyService(ApplyRepository applyRepository) {
        this.applyRepository = applyRepository;
    }

    public Apply createApplication(final Apply apply) {
       return applyRepository.save(apply);
    }

    public Apply updateApplication (final Apply apply) throws ApplicationNotFoundException {
        applyRepository.findById(apply.getApplyId()).orElseThrow(() -> new ApplicationNotFoundException(apply.getApplyId()));
        return applyRepository.save(apply);
    }

    public Apply deleteApplication(final Long applyId) throws ApplicationNotFoundException {
        Apply apply = applyRepository.findById(applyId).orElseThrow(() -> new ApplicationNotFoundException(applyId));
        applyRepository.delete(apply);
        return apply;
    }


    public Apply findById(final Long applyId) throws ApplicationNotFoundException {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
        return applyRepository.findById(applyId).orElseThrow(() -> new ApplicationNotFoundException(applyId));
    }

    public List<Apply> findAll() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
        return applyRepository.findAll();
    }

}
