package com.example;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.domain.Jkeyword;
import com.example.domain.JkeywordList;
import com.example.repository.*;
import com.example.repository.JdocrelationRepository;
import com.example.repository.Jdocrepository;
import com.example.repository.JkeywordRepository;
import com.example.repository.JsavedocRepository;
import com.example.repository.JuserRepository;
import com.example.domain.Jsavedoc;
import com.example.domain.Juser;
import com.example.api.naver.NaverConnection;
import com.example.api.watson.WatsonConnection;
import com.example.domain.Jdoc;
import com.example.domain.Jdocrelation;

@SessionAttributes("id")
@Controller
public class JAMLANDController {

	@Autowired
	JkeywordRepository jkeywordRepository;
	
	@Autowired
	Jdocrepository jdocRepository;
	
	@Autowired
	JdocrelationRepository jdocrelationRepository;
	
	@Autowired
	JsavedocRepository jsavedocRepository;
	
	@Autowired
	JuserRepository juserRepository;
	
	private static String NOUSER = "unknown";
	
	 @RequestMapping("/")
	    public String combinMain(Model model) {
	        return "JAMLAND";
	        }
	 
	 @RequestMapping("/jam_menu")
	    public String mainMenu(Model model) {
		 	List<Jkeyword> jkeywords = jkeywordRepository.findAll();
			String keywords = "";
			
			model.addAttribute("num", Integer.toString(jkeywords.size()));	
			//model.addAttribute("keywords", "음악대장%일상%하현우%"); for test
			
			for (Jkeyword jkeyword : jkeywords){
				keywords = keywords + "%" + jkeyword.getKo();
			}
			model.addAttribute("keywords", keywords); 
			
	        return "jam_menu";
	        }
	 
	 @RequestMapping("/jam_main")
	    public String main(Model model) {
		 List<Jdoc> jdocList = jdocRepository.findAll();		 
		 model.addAttribute("num", Integer.toString(jdocList.size())); 
	        return "jam_main";
	        }
	 
	 @RequestMapping("/jam_join")
	    public String join(Model model) {
	        return "jam_join";
	        }
	 
	 @RequestMapping("/jam_login")
	    public String login(Model model) {//,HttpSession session
		  
		 //System.out.println("\n" + session + ": 나의 세션");
	        return "jam_login";
	        }
	 
	 @RequestMapping("/login.do")
	    public String doLogin(@RequestParam("input1")String id,
	    							@RequestParam("input2")String pw, Model model, HttpSession session) {//,HttpSession session	  
		 //System.out.println("post 요청 와따 !" + id+ " : " + pw);
		 Juser juser = juserRepository.findOne(id);	
		 if(juser != null)
		 {
			 if(juser.getPw().equals(pw))
			 {
				session.setAttribute("id", id);
				 return "jam_main";
			 } 
		 }
		 
	        return "jam_login";
	        }
	 
	 @RequestMapping("/jam_list1")//keyword,num, dids, content_title, content_subtitle, content_keywords, content_ytb
	    public String jamList(@RequestParam(value="keyword", required=false, defaultValue="ALL") String keyword, Model model) {
	        
		 List<Jdocrelation> jrelations;
		 List<String> docList = new ArrayList<String>();
		 
		 if(keyword.equals("ALL"))
			 jrelations = jdocrelationRepository.findAll();		 
		 else
			 jrelations = jdocrelationRepository.findByKeyword(keyword);
		 
		 for (Jdocrelation rel : jrelations)//did를 가져온다
		 {
				docList.add(Integer.toString(rel.getDid()));
		 }
		 
		 
		 List<Jdoc> jdocList = new ArrayList<Jdoc>();
		 boolean toggle = true;//중복방지용
		for(int i=0; i< docList.size(); i++)
		{
				int index = Integer.parseInt(docList.get(i));
				for(int j=0; j< jdocList.size() ; j++)
				{
					if(jdocList.get(j).getDid() == index)
					{
						toggle = false;
						break;
					}
				}
				if(toggle)
				{
					Jdoc temp = jdocRepository.findByDid(index);
					jdocList.add(temp);
				}
				toggle = true;
		}
		
		int num = jdocList.size(); 		 		
		 String dids = "", content_title = "", content_subtitle = ""; 
		 String content_keywords = "", content_ytb = "";
		 
		 for (Jdoc jdoc : jdocList)
		 {
				dids = dids + "%" + jdoc.getDid();
				content_title = content_title + "%" + jdoc.getTitle();
				content_subtitle = content_subtitle + "%" + jdoc.getSubtitle();
				content_ytb = content_ytb + "%" + jdoc.getYtbId();
				
				List<Jdocrelation> temp = jdocrelationRepository.findByDid(jdoc.getDid());
				for(Jdocrelation rel : temp)
				{
					content_keywords = content_keywords + " + " + rel.getKeyword();
				}
				
				content_keywords = content_keywords + "%";
		}
		 	 
		 model.addAttribute("keyword", keyword);
		 model.addAttribute("num", Integer.toString(num));
		 if(num != 0)
		 {
			 model.addAttribute("dids", dids.substring(1));
			 model.addAttribute("content_title", content_title.substring(1));
			 model.addAttribute("content_subtitle", content_subtitle.substring(1));
			 model.addAttribute("content_keywords", content_keywords.substring(1));
			 model.addAttribute("content_ytb", content_ytb.substring(1));			 
		 }
		 
		  
	        return "jam_list1";
	    } 
	
	 @RequestMapping("/jam_mine")
	 public String myJamList(Model model, HttpSession session)
	 {
		 if(session.isNew())
			 return "jam_main";
		 String uid = (String)session.getAttribute("id");
		 List<Jsavedoc> jsavedocs = jsavedocRepository.findByUid(uid);
		 List<String> savedocList = new ArrayList<String>();
			
		for (Jsavedoc savedoc : jsavedocs)
		{
			savedocList.add(Integer.toString(savedoc.getDid()));
		}
		List<Jdoc> jdocList = new ArrayList<Jdoc>();
		
		for(int i=0; i< savedocList.size(); i++)
		{	
			int index = Integer.parseInt(savedocList.get(i));
			Jdoc temp = jdocRepository.findByDid(index);
			jdocList.add(temp);			
		}
				
		int num = jdocList.size(); 		 		
		 String dids = "", content_title = "", content_subtitle = ""; 
		 String content_keywords = "", content_ytb = "";
		 
		 for (Jdoc jdoc : jdocList)
		 {
				dids = dids + "%" + jdoc.getDid();
				content_title = content_title + "%" + jdoc.getTitle();
				content_subtitle = content_subtitle + "%" + jdoc.getSubtitle();
				content_ytb = content_ytb + "%" + jdoc.getYtbId();
				
				List<Jdocrelation> temp = jdocrelationRepository.findByDid(jdoc.getDid());
				for(Jdocrelation rel : temp)
				{
					content_keywords = content_keywords + " + " + rel.getKeyword();
				}
				
				content_keywords = content_keywords + "%";
		}
		 	 
		 model.addAttribute("keyword", uid);
		 model.addAttribute("num", Integer.toString(num));
		 if(num != 0)
		 {
			 model.addAttribute("dids", dids.substring(1));
			 model.addAttribute("content_title", content_title.substring(1));
			 model.addAttribute("content_subtitle", content_subtitle.substring(1));
			 model.addAttribute("content_keywords", content_keywords.substring(1));
			 model.addAttribute("content_ytb", content_ytb.substring(1));			 
		 }
		
		
		 return "jam_list1";
	 }
	
	@RequestMapping("/save.do")
	public String saveJam(@RequestParam(value="did", required=true) String did, Model model, HttpSession session) 
	{
		if(session.isNew())
			return "";
		
		Jsavedoc jsavedoc = new Jsavedoc();
		jsavedoc.setDid(Integer.parseInt(did));
		jsavedoc.setUid((String)session.getAttribute("id"));
		
		jsavedocRepository.save(jsavedoc);
		
		return "jam_main";
	}
	 
    @RequestMapping("/jam_context")
    public String jamContext(@RequestParam(value="did", required=true) String did, Model model) {
    	
    	Jdoc jdoc = jdocRepository.findByDid(Integer.parseInt(did));
    	String keywords = "";
    	List<Jdocrelation> temp = jdocrelationRepository.findByDid(jdoc.getDid());
		for(Jdocrelation rel : temp)
		{
			keywords = keywords + ", " + rel.getKeyword();
		}
    	
        model.addAttribute("did", did);
        model.addAttribute("title", jdoc.getTitle());
        model.addAttribute("subtitle", jdoc.getSubtitle());
        model.addAttribute("ytb", jdoc.getYtbId());
        model.addAttribute("context1", jdoc.getContext1());
        model.addAttribute("context2", jdoc.getContext2());
        model.addAttribute("keywords", keywords);
        
        return "jam_context";
    }
    
    @RequestMapping("/jam_plus")
    public String write(@RequestParam(value="did", required=false, defaultValue="null")String did, 
    										Model model, HttpSession session) {
        //model.addAttribute("name", name);
    	if(did.equals("null"))
    		return "jam_plus";
    	
    	String id = NOUSER;
    	if(!session.isNew())
    		id = (String)session.getAttribute("id");
    	
    	Jdoc jdoc = jdocRepository.findByDid(Integer.parseInt(did));
    	String keywords = "";
    	List<Jdocrelation> temp = jdocrelationRepository.findByDid(jdoc.getDid());
		for(Jdocrelation rel : temp)
		{
			keywords = keywords + "%" + rel.getKeyword();
		}
    	
        model.addAttribute("did", did);
        model.addAttribute("title", jdoc.getTitle());
        model.addAttribute("subtitle", jdoc.getSubtitle());
        model.addAttribute("ytb", jdoc.getYtbId());
        model.addAttribute("context1", jdoc.getContext1());
        model.addAttribute("context2", jdoc.getContext2());
        if(keywords.length() != 1)
        	model.addAttribute("keywords", keywords.substring(1));
    	    	
    		return "jam_plus";
        }
    
    @RequestMapping("/plus.do")
    public String doPlus(@RequestParam(value="input_did", required=false, defaultValue="0") String did,
    				@RequestParam("input_title")String title,
								@RequestParam("input_subtitle")String subtitle, 
									@RequestParam("input_ytb")String ytb, 
										@RequestParam("input_context1")String context1,
										@RequestParam("input_context2")String context2,
											@RequestParam("input_keywords")String keywords, Model model, HttpSession session)
    {   	
    	Jdoc jdoc = new Jdoc();
    	if(!did.equals(0))
    		jdoc.setDid(Integer.parseInt(did));   	
    	jdoc.setTitle(title);
    	jdoc.setSubtitle(subtitle);
    	jdoc.setYtbId(ytb);
    	jdoc.setContext1(context1);
    	jdoc.setContext2(context2);
    	
    	if(session.isNew())
    		jdoc.setUid(NOUSER);
    	else
    		jdoc.setUid((String)session.getAttribute("id"));    	
    	jdoc.setRcmd(0);
    	jdoc.setRprt(0);
    	
    	jdoc.setMdfydate(new Date(Calendar.getInstance().getTimeInMillis()));
    	Jdoc jdoc2 =jdocRepository.save(jdoc);
    	
    	int newDid = jdoc2.getDid();
    	System.out.println(newDid + " : " + jdoc2.getContext2());
    	/******************************************************/
    	String[] keyword = keywords.split("%");
    	NaverConnection naver = new NaverConnection();
    	WatsonConnection watson;
    	
    	for(int i=0; i< keyword.length ; i++)
    	{
    		Jkeyword jkeyword = new Jkeyword();
    		String ko = keyword[i];    		
    		jkeyword.setKo(ko);
    		
    		String en = naver.koToEn(ko);
    		//System.out.println(en);
    		jkeyword.setEn(en);
    		
    		watson = new WatsonConnection(en);
    		
    		jkeyword.setType(watson.mostEmotion());
    		jkeyword.setScore(watson.mostEmotionScore());
    		
    		Jkeyword jkeyword2 = jkeywordRepository.save(jkeyword);
    		
    		/***********************************************/
    		
    		Jdocrelation jdocrelation = jdocrelationRepository.findByDidAndKeyword(newDid, jkeyword2.getKo());
    		if(jdocrelation == null)
    		{
    			jdocrelation = new Jdocrelation();
    			jdocrelation.setDid(newDid);
        		jdocrelation.setKeyword(jkeyword2.getKo());
        		
        		jdocrelationRepository.save(jdocrelation);   
    		}   		 		
    	} 
       	
    	return "jam_main";
    }
    
    @RequestMapping("/join.do")
    public String doJoin(@RequestParam("input_id")String id,
    							@RequestParam("input_pw")String pw, 
    							@RequestParam("input_nick")String nick, 
    							@RequestParam("input_mail")String mail, Model model) {//,HttpSession session	  
	 //System.out.println("join 요청 와따 !" + id+ " : " + pw);
	 Juser juser = new Juser();
	 juser.setId(id);
	 juser.setPw(pw);
	 juser.setName(nick);
	 juser.setMail(mail);
	 juserRepository.save(juser);
	 
        return "jam_main";
      }
    
    @RequestMapping("/emotion/{et}")
	public String emotionToDocList(@PathVariable String et, Model model)
	{
		List<Jdoc> alldocs = jdocRepository.findAll();
		
		List<Jkeyword> keywords = jkeywordRepository.findByJtypeOrderByScoreDesc(et); //키워드의 score가 큰 순서대로.
		double[] scores = new double[alldocs.size() + 1];	//문서의 개수만큼!
		for(int i=0; i< scores.length; i++) // score들 초기화.
		{
			scores[i] = 0;
		}
		
		/*****************************************************/
		for(int i=0; i < keywords.size() ; i++)
		{
			Jkeyword current_keyword = keywords.get(i);
			double current_score = current_keyword.getScore();
			List<Jdocrelation> current_relations 
						= jdocrelationRepository.findByKeyword(current_keyword.getKo());
			
			for(int j=0; j < current_relations.size() ; j++)
			{
				scores[current_relations.get(j).getDid()] = scores[current_relations.get(j).getDid()] + current_score;
			}
		}		
		/*****************************************************/
		
		int[] top10List = new int[10];
		
		for(int i=0; i < top10List.length ; i++)
		{
			top10List[i] =0;	//초기호ㅏ
			double max = 0;
			int current_index=0;
			
			for(int j=0; j < scores.length ; j++)
			{
				if(scores[j] != 0)
				{												
					if(scores[j] > max)
					{
						max = scores[j];
						current_index = j;
						//System.out.println(j + "현재 j");
					}							
				}				
			}
			
			scores[current_index] = 0;
			top10List[i] = current_index;
			max = 0;
			current_index = 0;			
			//System.out.println(i + "번째 : " + top10List[i]);
		}
		/*****************************************************/			
		
		List<Jdoc> topdocs = new ArrayList<Jdoc>();
		for(int i=0; i < top10List.length; i++)
		if(top10List[i] != 0)
		{
			topdocs.add(jdocRepository.findByDid(top10List[i]));
		}
		
		int num = topdocs.size(); 		 		
		 String dids = "", content_title = "", content_subtitle = ""; 
		 String content_keywords = "", content_ytb = "";
		 
		 for (Jdoc jdoc : topdocs)
		 {
				dids = dids + "%" + jdoc.getDid();
				content_title = content_title + "%" + jdoc.getTitle();
				content_subtitle = content_subtitle + "%" + jdoc.getSubtitle();
				content_ytb = content_ytb + "%" + jdoc.getYtbId();
				
				List<Jdocrelation> temp = jdocrelationRepository.findByDid(jdoc.getDid());
				for(Jdocrelation rel : temp)
				{
					content_keywords = content_keywords + " + " + rel.getKeyword();
				}
				
				content_keywords = content_keywords + "%";
		}
		 	 
		 model.addAttribute("keyword", et);
		 model.addAttribute("num", Integer.toString(num));
		 if(num != 0)
		 {
			 model.addAttribute("dids", dids.substring(1));
			 model.addAttribute("content_title", content_title.substring(1));
			 model.addAttribute("content_subtitle", content_subtitle.substring(1));
			 model.addAttribute("content_keywords", content_keywords.substring(1));
			 model.addAttribute("content_ytb", content_ytb.substring(1));			 
		 }
		 		
		return "jam_list1";
	}
    
    @RequestMapping(value = "/image", method = RequestMethod.GET, produces = "image/png")
    public @ResponseBody byte[] getFile(@RequestParam(value="name", required=false, defaultValue="search_icon.png") String name) {
        try {
        	
        	
            InputStream inputStream = this.getClass().getResourceAsStream("images/" + name);
                   
            BufferedImage img = ImageIO.read(inputStream);
            ByteArrayOutputStream bao = new ByteArrayOutputStream();
            ImageIO.write(img, "png", bao);
            return bao.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    @RequestMapping("/jam_main")
//    public String gomain(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
//        model.addAttribute("name", name);
//        return "jam_main";
//    }
    
  
    
}