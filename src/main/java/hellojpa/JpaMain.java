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
            Member member1 = new Member(150L, "A");
            Member member2 = new Member(160L, "B");
            /**
             * 영속 - 이때 DB에 저장되는게 아니다. 영속 컨텍스트에 저장됨
             */
            em.persist(member1);
            em.persist(member2);

            System.out.println("================");

            /**
             * DB는 영속 한 이후 commit 을 해야 저장됨
             */
            tx.commit();

        } catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
