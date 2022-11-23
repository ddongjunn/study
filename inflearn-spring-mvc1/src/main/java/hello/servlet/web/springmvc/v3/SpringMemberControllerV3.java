package hello.servlet.web.springmvc.v2;

import hello.servlet.web.domain.member.Member;
import hello.servlet.web.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @RequestMapping(value = "/new-form", method = RequestMethod.GET)
    public String newForm(){
        return "new-form";
    }

    // /springmvc/v3/members
    @RequestMapping(method = RequestMethod.GET)
    public String members(Model model) {

        List<Member> members = memberRepository.findAll();

        model.addAttribute("members",members);
        return "members";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String members(@RequestParam("username") String username,
                          @RequestParam("age") int age,
                          Model model) {

        Member member = new Member(username, age);
        memberRepository.save(member);

        model.addAttribute("member",member);
        return "save-result";
    }

}
