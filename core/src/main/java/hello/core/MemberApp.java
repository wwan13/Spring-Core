package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

public class MemberApp {

    public static void main(String[] args) {

        /* AppConfig 를 통해 memberservice 인스턴스 생성 -> client 코드에서 직접 건들지 않아도 됨 */
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();

        /* 멤버 생성 후 테스트 */
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find member = " + findMember.getName());
    }
}
