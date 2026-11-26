package com.dataman.toolkit.changelog;

import com.dataman.base.paging.PageResult;
import com.dataman.toolkit.changelog.ModifyReasonService.ReasonRow;
import com.dataman.toolkit.crud.PageParams;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** Reason list of the "why are you changing this" pop-up. */
@RestController
@RequestMapping("/api/modify-reasons")
public class ModifyReasonController {

    private final ModifyReasonService service;

    public ModifyReasonController(ModifyReasonService service) {
        this.service = service;
    }

    @GetMapping
    public PageResult<ReasonRow> reasons(@RequestParam(required = false) Integer start, @RequestParam(required = false) Integer size,
            @RequestParam(required = false) String q, @RequestParam(required = false) Boolean end) {
        return service.reasons(PageParams.of(start, size, "name", q, "name", false, end));
    }
}
