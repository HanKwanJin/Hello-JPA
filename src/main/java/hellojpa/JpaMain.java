package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        /**
         * 원래는 이런 코드들도 다 쓰지 않고 persist 하나만 호출하면 된다.
         * WHY? 나머지 코드들은 스프링이 알아서 다 해준다.
         */
        try{


            /**
             * 멤버를 찾아온 다음에 값을 변경해주면 JPA 가 자동으로
             * update 쿼리를 날려준다.
             * --> 변경 감지
             */
            Member member = em.find(Member.class, 150L);
            member.setName("ZZZZZ");
            System.out.println("=======");
            tx.commit();

        } catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
