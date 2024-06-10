package com.example.demo.base;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@SessionAttributes(types = ReturnColorsBalls.class)
@Controller
public class IndexController {
	@Autowired
	//public ItemsService itemsService;

	@ModelAttribute("returnColorsBalls")
	public ReturnColorsBalls setReturnColorsBalls() {
		return new ReturnColorsBalls();
	}

	@GetMapping("/")
	public String top(@ModelAttribute ReturnColorsBalls returnColorsBalls, Model model) {
		model.addAttribute("list", returnColorsBalls);
		return "index";
	}

	@GetMapping("/index")
	public String index(@ModelAttribute ReturnColorsBalls returnColorsBalls, Model model) {
		model.addAttribute("list", returnColorsBalls);
		return "index";
	}

	int[] judgeColor(int[] anser, int[] input) {
		int[] output = { 0, 0 };
		for (int i = 0; i < input.length; i++) {
			for (int j = 0; j < input.length; j++) {
				if (i == j) {
					if (anser[i] == input[j]) {
						output[0]++;
					}

				} else {
					if (anser[i] == input[j]) {
						output[1]++;
					}
				}
			}
		}
		return output;
	}

	public int[] makeAnswer() {
		int[] answer = { 0, 0, 0, 0 };
		for (int i = 0; i < 4; i++) {
			boolean dub = true;
			while (dub) {
				dub = false;
				answer[i] = (int) ((Math.random() * 6) + 1);
				for (int j = 0; j < i; j++) {
					if (answer[i] == answer[j]) {
						dub = true;
						break;

					}
				}
			}
		}
		return answer;

	}

	@GetMapping("/game")
	public String game(@ModelAttribute ReturnColorsBalls returnColorsBalls, @ModelAttribute InputBallColor balls,
			Model model) {
		int[] answer = makeAnswer();

		returnColorsBalls.setAnswer(answer);
		returnColorsBalls.reset();
		model.addAttribute("list", returnColorsBalls);
		model.addAttribute("balls", balls);
		return "game";
	}

	@PostMapping("/gameStart")
	public String gameStart(@ModelAttribute ReturnColorsBalls returnColorsBalls, @ModelAttribute InputBallColor balls,
			Model model) {
		int[] answer = makeAnswer();

		returnColorsBalls.setAnswer(answer);
		returnColorsBalls.reset();
		model.addAttribute("list", returnColorsBalls);
		model.addAttribute("balls", balls);
		return "game";
	}

	@PostMapping("/gameSend")
	public String send(@ModelAttribute ReturnColorsBalls returnColorsBalls, @ModelAttribute InputBallColor balls,
			Model model) {
		int[] answer = returnColorsBalls.getAnswer();
		System.out.println("gameSend実行");
		System.out.println(balls.getBall1());
		System.out.println(balls.getBall2());
		System.out.println(balls.getBall3());
		System.out.println(balls.getBall4());
		for (int i = 0; i < answer.length; i++) {
			System.out.println(answer[i]);
		}
		int[] input = balls.returnIntBallColors();
		int[] temp = judgeColor(answer, input);
		System.out.println("ヒット:" + temp[0]);
		System.out.println("ブロー:" + temp[1]);
		returnColorsBalls.setColors(balls, temp[0], temp[1]);
		model.addAttribute("list", returnColorsBalls);
		model.addAttribute("balls", balls);

		if (returnColorsBalls.getBalls()[returnColorsBalls.getCount() - 1].getBalls()[4] == 4) {
			returnColorsBalls.setScore(returnColorsBalls.getScore() + 13 - returnColorsBalls.getCount() - 1);
			return "game2";
		}

		return "game";
	}

	@GetMapping("/gameAn")
	public String gameAn(@ModelAttribute ReturnColorsBalls returnColorsBalls, @ModelAttribute InputBallColor balls,
			Model model) {
		int[] answer = makeAnswer();

		returnColorsBalls.setAnswer(answer);
		returnColorsBalls.reset();
		model.addAttribute("list", returnColorsBalls);
		model.addAttribute("balls", balls);
		return "gameAnmika";
	}

	@PostMapping("/gameStartAn")
	public String gameStartAn(@ModelAttribute ReturnColorsBalls returnColorsBalls, @ModelAttribute InputBallColor balls,
			Model model) {
		int[] answer = makeAnswer();

		returnColorsBalls.setAnswer(answer);
		returnColorsBalls.reset();
		model.addAttribute("list", returnColorsBalls);
		model.addAttribute("balls", balls);
		return "gameAnmika";
	}

	@PostMapping("/gameSendAn")
	public String sendAn(@ModelAttribute ReturnColorsBalls returnColorsBalls, @ModelAttribute InputBallColor balls,
			Model model) {
		int[] answer = returnColorsBalls.getAnswer();
		System.out.println("gameSend実行");
		System.out.println(balls.getBall1());
		System.out.println(balls.getBall2());
		System.out.println(balls.getBall3());
		System.out.println(balls.getBall4());
		for (int i = 0; i < answer.length; i++) {
			System.out.println(answer[i]);
		}
		int[] input = balls.returnIntBallColors();
		int[] temp = judgeColor(answer, input);
		System.out.println("ヒット:" + temp[0]);
		System.out.println("ブロー:" + temp[1]);
		returnColorsBalls.setColors(balls, temp[0], temp[1]);
		model.addAttribute("list", returnColorsBalls);
		model.addAttribute("balls", balls);

		if (returnColorsBalls.getBalls()[returnColorsBalls.getCount() - 1].getBalls()[4] == 4) {
			returnColorsBalls.setScore(returnColorsBalls.getScore() + (13 - returnColorsBalls.getCount() - 1) * 10);
			return "gameAnmika2";
		}

		return "gameAnmika";
	}

	@GetMapping("/gacha")
	public String gacha(@ModelAttribute ReturnColorsBalls returnColorsBalls, Model model) {

		model.addAttribute("list", returnColorsBalls);
		return "gacha";
	}

	@PostMapping("/gachaSubmit")
	public String gachaSubmit(@ModelAttribute ReturnColorsBalls returnColorsBalls, Model model) {
		if (returnColorsBalls.getScore() <= 0) {
			return "gacha3";
		}
		//List<Items> list = itemsService.findAll();
		//int item = (int) (Math.random() * list.size());
		//Items itemd = list.get(item);
		//System.out.println(list);
		//System.out.println(itemd.getFilename());

		//returnColorsBalls.setUrl("/img/" + itemd.getFilename());
		//returnColorsBalls.addItems("/img/" + itemd.getFilename());
		//returnColorsBalls.setScore(returnColorsBalls.getScore() - 1);

		model.addAttribute("list", returnColorsBalls);
		return "gacha2";
	}
	
	@PostMapping("/gachaSubmit10")
	public String gachaSubmit10(@ModelAttribute ReturnColorsBalls returnColorsBalls, Model model) {
		if (returnColorsBalls.getScore() < 10) {
			return "gacha3";
		}
//		List<Items> list = itemsService.findAll();
//		String[] urls=new String[10];
//		for(int i =0;i<urls.length;i++) {
//			int item = (int) (Math.random() * list.size());
//			Items itemd = list.get(item);
//			urls[i]="/img/" + itemd.getFilename();
//			returnColorsBalls.addItems("/img/" + itemd.getFilename());
//		}
//		
//		returnColorsBalls.setUrls(urls);
//		returnColorsBalls.setScore(returnColorsBalls.getScore() - 10);

		model.addAttribute("list", returnColorsBalls);
		return "gacha102";
	}
	

	@GetMapping("/items")
	public String items(@ModelAttribute ReturnColorsBalls returnColorsBalls, Model model) {
		if (returnColorsBalls.getItemsList().size() == 0) {
			return "items2";
		}

		model.addAttribute("list", returnColorsBalls);
		return "items";
	}

	@GetMapping("/test")
	public String tset() throws Exception{
		if (true) {
			throw new Exception();
		}

		return "index";
	}
	
	@GetMapping("/seki")
	public String seki(@ModelAttribute ReturnColorsBalls returnColorsBalls,Model model){
		ArrayList<String> names= new ArrayList<>();
		String[] str ={"赤平","大濵","嘉数","笠原","三井","加藤","川口","黒須","小山","砂田","須波","津嘉山","富山","長崎","中塚","野崎","平塚","松本","丸山"};
		for (int i = str.length - 1; i > 0; i--) {
		    int r = (int) (Math.random() * (i + 1));
		    String tmp = str[i];
		    str[i] = str[r];
		    str[r] = tmp;
		}
		model.addAttribute("names", str);
		return "seki";
	}

}
