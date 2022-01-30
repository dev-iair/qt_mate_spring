package qt.mate.qt_mate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import qt.mate.qt_mate.model.MemberDTO;
import qt.mate.qt_mate.service.MemberService;

@RestController
public class MemberController {

    @Autowired
    private MemberService memberService;

    @RequestMapping(value = "/member")
    public String showMember(){
        MemberDTO memberDTO = new MemberDTO();
        String name = "이진혁";
        memberDTO = memberService.showMember(name);
        System.out.println(memberDTO.getPhone());
        return null;
    }
    
}
