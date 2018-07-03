package co.nz.equifax.task.controllers;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.nz.equifax.commons.web.ApiResponse;
import co.nz.equifax.commons.web.EmptyJsonResponse;
import co.nz.equifax.security.UserPrincipal;
import co.nz.equifax.task.entities.Task;
import co.nz.equifax.task.repository.TaskRepository;
import co.nz.equifax.task.responses.ScoreResponse;
import co.nz.equifax.task.services.BusinessService;
import co.nz.equifax.utils.Constants;

@RestController
@RequestMapping("/score")
public class ScoreController {

	@Autowired
	private BusinessService businessService;

	@GetMapping
	public ResponseEntity getScore(Principal principal) {
		ScoreResponse resp = new ScoreResponse();
		// String username= ((UserPrincipal)principal).getUsername();
		resp.setScore(businessService.getScore());
		String labels[] = { LocalDate.now().format(Constants.formatter).toString(),
		        LocalDate.now().minusDays(7).format(Constants.formatter).toString(),
		        LocalDate.now().minusDays(14).format(Constants.formatter).toString(),
		        LocalDate.now().minusDays(21).format(Constants.formatter).toString(),
		        LocalDate.now().minusDays(28).format(Constants.formatter).toString(),
		        LocalDate.now().minusDays(35).format(Constants.formatter).toString(),
		        LocalDate.now().minusDays(42).format(Constants.formatter).toString() };
		resp.setLabels(labels);
		String series[] = { "Score" };
		resp.setSeries(series);
		String[] data = { resp.getScore(), businessService.getScore(), businessService.getScore(),
		        businessService.getScore(), businessService.getScore(), businessService.getScore(),
		        businessService.getScore() };
		resp.setData(data);
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, "Customer Score", resp));
	}

}
