package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {

        AppConfig appConfig = new AppConfig();

        // 1. 조회 : 호출할 때 마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();

        // 2. 조회 : 호출할 때 마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        // 참조값이 다른 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        // memberService1 != memberService2
        Assertions.assertThat(memberService1).isNotSameAs(memberService2);


        /*
        지금까지 사용 했던 순수 자바 DI 컨테이너 AppConfig 는 요청 할때마다 새로운 객체가 생성됨
        -> 메모리 낭비가 심함

        solution : 해당 객체가 딱 1번만 생성 되고 공유하도록 설계 하면 됨 => singleton pattern
         */

    }

    @Test
    @DisplayName("싱글톤 패턴을 사용한 객체 사용")
    void singletonServiceTest() {
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);

        Assertions.assertThat(singletonService1).isSameAs(singletonService2);
        /*
        same : 인스턴스가 같은지
        equal : == 연산자와 같은 역할
         */
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer() {

        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        // 1. 조회 : 호출할 때 마다 객체를 생성
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);

        // 2. 조회 : 호출할 때 마다 객체를 생성
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        // 참조값이 같은 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        // memberService1 == memberService2
        Assertions.assertThat(memberService1).isSameAs(memberService2);

    }
}
