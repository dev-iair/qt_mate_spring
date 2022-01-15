package qt.mate.qt_mate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import qt.mate.qt_mate.model.MemberDTO;
import qt.mate.qt_mate.service.MemberService;

@Controller
@RequestMapping(value = "/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @RequestMapping(value = "/")
    public String showMember(){
        MemberDTO memberDTO = new MemberDTO();
        String name = "이진혁";
        memberDTO = memberService.showMember(name);
        return null;
    }
    
}
