package com.example.SimpleLoginApplication.LoginApplication.UserController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.SimpleLoginApplication.LoginApplication.entity.User;
import com.example.SimpleLoginApplication.LoginApplication.model.QuestionAndAnswers;
import com.example.SimpleLoginApplication.LoginApplication.service.EmailService;
import com.example.SimpleLoginApplication.LoginApplication.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService service;

	@Autowired
	private EmailService emailService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@PostMapping("/registeruser")
	public User registerUser(@RequestBody User user) throws Exception {
		System.out.println("Email :" + user.getEmail());
		String emailId = user.getEmail();
		if (emailId != null && !"".equals(emailId)) {
			User userObj = service.fetchByEmailId(emailId);
			if (userObj != null) {
				throw new Exception("user with " + emailId + " is already exists");
			}
		}
		user.setStatus("pending");
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		Optional<Object> userObject = Optional.of(service.saveUser(user));
		userObject.ifPresent(u -> {
			try {
				String token = UUID.randomUUID().toString();
				User userToken = (User) userObject.get();
				userToken.setToken(token);
				service.saveUser(userToken);
				emailService.sendMail(user);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		return (User) userObject.get();

	}

	@PostMapping("/loginuser")
	public User loginUser(@RequestBody User user) throws Exception {
		String emailId = user.getEmail();
		String pass = user.getPassword();
		User userObj = null;
		if (emailId != null && pass != null) {
			userObj = service.fetchUserByEmailIdAndPassword(emailId, pass);
		}

		if (userObj == null) {
			throw new Exception("Bad Credentials");
		}
		return userObj;

	}

	@GetMapping(value = "/activation")
	public String activation(@RequestParam("token") String token) {
		User user = service.findByToken(token);
		if (user.getToken() == null) {
			System.out.print("invalid token");
		} else {
			user.setStatus("Verified");
			service.saveUser(user);
		}
		return "activated";

	}

	@GetMapping(value = "/getQuestions")
	public List<QuestionAndAnswers> randomQuestionAnswers() {

		List<QuestionAndAnswers> listOfQuestions = new CopyOnWriteArrayList<>();
		List<QuestionAndAnswers> listOfQuestionsNew = new CopyOnWriteArrayList<>();

		listOfQuestions = getQuestionAnswers();
		QuestionAndAnswers ques1 = new QuestionAndAnswers();
		Iterator<QuestionAndAnswers> userQuestion = listOfQuestions.iterator();
		List<QuestionAndAnswers> randomQuestion = new CopyOnWriteArrayList<>();
		Random random = new Random(); // to get random array

			while (userQuestion.hasNext()) {
				ques1 = userQuestion.next();
				ques1.setAnswer("");
				listOfQuestionsNew.add(ques1);
				System.out.println("list " +listOfQuestionsNew);
				
			}
				for (int i = 0; i <= 4; i++) {
				int r = random.nextInt(listOfQuestionsNew.size());// assign random question to current question.
				randomQuestion.add(listOfQuestionsNew.get(r));
				System.out.println("Question " + randomQuestion.size());
			}
		return randomQuestion;
	}

	@PostMapping("/verifyAnswers")
	public String verifyAnswers(@RequestBody List<QuestionAndAnswers> userQuestions) {

		List<QuestionAndAnswers> allQuestions = getQuestionAnswers();

		QuestionAndAnswers ques1 = null;
		QuestionAndAnswers ques2 = null;

		Iterator<QuestionAndAnswers> userQuestion = userQuestions.iterator();
		while (userQuestion.hasNext()) {
			ques1 = userQuestion.next();
			// System.out.println(ques1);

		}
		Iterator<QuestionAndAnswers> commonQuestion = allQuestions.iterator();
		while (commonQuestion.hasNext()) {
			ques2 = commonQuestion.next();
			if (ques1.getQuestion().contains(ques2.getQuestion()) && ques1.getAnswer().contains(ques2.getAnswer())) {
				// System.out.println(ques2);
				return "correct";
			}
		}
		return "No Data found Or Incorrect Answer";

	}

	public List<QuestionAndAnswers> getQuestionAnswers() {

		QuestionAndAnswers q1 = new QuestionAndAnswers("Solve 35 / .07", "500");
		QuestionAndAnswers q2 = new QuestionAndAnswers("Solve 136.09/43.9", "3.1");
		QuestionAndAnswers q3 = new QuestionAndAnswers("Solve 0.006 / ? = 0.6", "0.01");
		QuestionAndAnswers q4 = new QuestionAndAnswers("Is Java is platform Independent language", "Yes");
		QuestionAndAnswers q5 = new QuestionAndAnswers("String is Mutable", "No");
		QuestionAndAnswers q6 = new QuestionAndAnswers("String Buffer is Mutable", "Yes");
		QuestionAndAnswers q7 = new QuestionAndAnswers("Java provide multiple inheritance?", "No");
		QuestionAndAnswers q8 = new QuestionAndAnswers("int i =200; double d=i;", "200.0");
		QuestionAndAnswers q9 = new QuestionAndAnswers("final int a = 10 possible?", "Yes");
		QuestionAndAnswers q10 = new QuestionAndAnswers("10+30", "40");
		List<QuestionAndAnswers> questionAndAnswers = new ArrayList<>();
		{
			questionAndAnswers.add(q1);
			questionAndAnswers.add(q2);
			questionAndAnswers.add(q3);
			questionAndAnswers.add(q4);
			questionAndAnswers.add(q5);
			questionAndAnswers.add(q6);
			questionAndAnswers.add(q7);
			questionAndAnswers.add(q8);
			questionAndAnswers.add(q9);
			questionAndAnswers.add(q10);

		}
		return questionAndAnswers;

	}
}
