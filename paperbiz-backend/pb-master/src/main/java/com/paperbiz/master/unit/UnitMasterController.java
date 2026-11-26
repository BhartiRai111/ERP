package com.paperbiz.master.unit;

import com.dataman.base.paging.PageResult;
import com.dataman.toolkit.changelog.ModifyReason;
import com.dataman.toolkit.crud.PageParams;
import com.paperbiz.master.unit.UnitMasterRepository.FindRow;
import com.paperbiz.master.unit.UnitMasterRepository.Lookup;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** REST of the Unit Master entry; contract as in docs/design/library-design.md section 3. */
@RestController
@RequestMapping("/api/master/unit-master")
public class UnitMasterController {

    private final UnitMasterService service;

    public UnitMasterController(UnitMasterService service) {
        this.service = service;
    }

    /** Values a new record starts with: the default GST unit (or nulls). */
    @GetMapping("/defaults")
    public Map<String, String> defaults() {
        return service.defaults().map(l -> Map.of("gstUnitCode", l.code(), "gstUnitName", l.name()))
                .orElse(Map.of("gstUnitCode", "", "gstUnitName", ""));
    }

    @GetMapping("/navigator")
    public List<String> navigator() {
        return service.navigator();
    }

    @GetMapping("/{code}")
    public UnitMasterDto open(@PathVariable String code) {
        return service.open(code);
    }

    @PostMapping
    public Map<String, String> create(@RequestBody UnitMasterDto body) {
        return Map.of("code", service.create(body));
    }

    @PutMapping("/{code}")
    public Map<String, String> update(@PathVariable String code, @RequestBody UnitMasterDto body) {
        return Map.of("code", service.update(code, body));
    }

    @DeleteMapping("/{code}")
    public void delete(@PathVariable String code, @RequestBody ModifyReason reason) {
        service.delete(code, reason);
    }

    @GetMapping("/find")
    public PageResult<FindRow> find(@RequestParam(required = false) Integer start, @RequestParam(required = false) Integer size,
            @RequestParam(required = false) String column, @RequestParam(required = false) String q,
            @RequestParam(required = false) String sort, @RequestParam(required = false) Boolean desc,
            @RequestParam(required = false) Boolean end) {
        return service.find(PageParams.of(start, size, column, q, sort, desc, end));
    }

    @GetMapping("/lookups/gst-unit")
    public PageResult<Lookup> gstUnits(@RequestParam(required = false) Integer start, @RequestParam(required = false) Integer size,
            @RequestParam(required = false) String q, @RequestParam(required = false) Boolean end) {
        return service.gstUnits(PageParams.of(start, size, "name", q, "name", false, end));
    }

    @GetMapping("/lookups/unit-nature")
    public List<Lookup> natures(@RequestParam(required = false) String q) {
        return service.natures(q);
    }

    @GetMapping("/suggestions")
    public PageResult<String> suggestions(@RequestParam(required = false) Integer start, @RequestParam(required = false) Integer size,
            @RequestParam(required = false) String q) {
        return service.suggestions(PageParams.of(start, size, "name", q, "name", false, false));
    }
}
