package hello.core.singleton;

public class SingletonService {

    // static 키워드를 사용하게 되면 객체가 딱 하나만 생성됨
    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance() {
        return instance;
    }

    // 다른 클래스에서 생성자를 통해 새로운 인스턴스를 만들 수 없도록 private 을 사용
    private SingletonService() {

    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }

}
