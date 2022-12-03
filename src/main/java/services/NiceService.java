package services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import actions.views.EmployeeConverter;
import actions.views.EmployeeView;
import actions.views.NiceConverter;
import actions.views.NiceView;
import actions.views.ReportConverter;
import actions.views.ReportView;
import constants.AttributeConst;
import constants.JpaConst;
import models.Nice;

/**
 * いいねテーブルの操作に関わる処理を行うクラス
 */
public class NiceService  extends ServiceBase {

    /**
     * 日報のいいねフラグをtrueにする
     * @param rv 日報データ
     * @param ev 従業員データ
     * @return 処理が完了した場合true
    */
    public boolean doNice(ReportView rv,EmployeeView ev) {
        try {
            Nice r = em.createNamedQuery(JpaConst.Q_NICE_GET_MINE_NICE_TO_REPORT,Nice.class)
                .setParameter(JpaConst.JPQL_PARM_REPORT, ReportConverter.toModel(rv))
                .setParameter(JpaConst.JPQL_PARM_EMPLOYEE, EmployeeConverter.toModel(ev))
                .getSingleResult();
            em.getTransaction().begin();
            r.setNiceFlag(JpaConst.NICE_REP_TRUE);
            r.setNicedAt(LocalDateTime.now());
            em.getTransaction().commit();

            return true;
        }catch(NoResultException e) {
            e.printStackTrace();

            return false;
        }
    }

    /**
     * 日報のいいねフラグをfalseにする
     * @param rv 日報データ
     * @param ev 従業員データ
     * @return 処理が完了した場合true
    */
    public boolean cancelNice(ReportView rv,EmployeeView ev) {
        try {
            Nice r = em.createNamedQuery(JpaConst.Q_NICE_GET_MINE_NICE_TO_REPORT,Nice.class)
                .setParameter(JpaConst.JPQL_PARM_REPORT, ReportConverter.toModel(rv))
                .setParameter(JpaConst.JPQL_PARM_EMPLOYEE, EmployeeConverter.toModel(ev))
                .getSingleResult();
            em.getTransaction().begin();
            r.setNiceFlag(JpaConst.NICE_REP_FALSE);
            r.setNicedAt(LocalDateTime.now());
            em.getTransaction().commit();

            return true;
        }catch(NoResultException e) {
            e.printStackTrace();

            return false;
        }
    }

    /**
     * 指定した日報についたいいねの件数を取得し、返却する
     * @param rv ReportView
     * @return いいねの件数
     */
    public long countAllNiceToReport(ReportView rv) {

        long count = (long)em.createNamedQuery(JpaConst.Q_NICE_ALL_NICE_COUNT_TO_REPORT,Long.class)
                .setParameter(JpaConst.JPQL_PARM_REPORT, ReportConverter.toModel(rv))
                .getSingleResult();

        return count;
    }


    /**
     * 日報のリストをもとにいいね数のリストを作成し、返却する
     * @param reportViewList 日報のリスト
     * @return いいね数のリスト
    */
    public List<Long> getAllCountNiceToReport(List<ReportView> reportViewList){
        List<Long> niceCount = new ArrayList<Long>();
        for(ReportView rv : reportViewList){
            niceCount.add(countAllNiceToReport(rv));
        }
        return niceCount;


    }

    /**
     * 日報に関する自分のいいねデータの件数を取得する
     * @param rv 日報データ
     * @param ev 従業員データ
     * @return 自分のいいねデータの件数
     */
    public long countCreatedMineNiceDataToReport(ReportView rv,EmployeeView ev) {

        long count = (long)em.createNamedQuery(JpaConst.Q_NICE_COUNT_CREATED_MINE_NICE_DATA_TO_REPORT,Long.class)
                .setParameter(JpaConst.JPQL_PARM_REPORT, ReportConverter.toModel(rv))
                .setParameter(JpaConst.JPQL_PARM_EMPLOYEE, EmployeeConverter.toModel(ev))
                .getSingleResult();

        return count;
    }

    /**
     * 日報に従業員がいいねした件数を取得する
     * @param rv 日報データ
     * @param ev 従業員データ
     * @return 自分のいいねの件数
     */
    public long countMineNiceToReport(ReportView rv,EmployeeView ev) {

        long count = (long)em.createNamedQuery(JpaConst.Q_NICE_COUNT_MINE_NICE_TO_REPORT,Long.class)
                .setParameter(JpaConst.JPQL_PARM_REPORT, ReportConverter.toModel(rv))
                .setParameter(JpaConst.JPQL_PARM_EMPLOYEE, EmployeeConverter.toModel(ev))
                .getSingleResult();

        return count;
    }

    /**
     * 従業員用の日報いいねテーブルを作成する(既にある場合は作成しない)
     * @param rv 日報データ
     * @param ev 従業員データ
     */
    public void create(ReportView rv,EmployeeView ev) {
        if(countCreatedMineNiceDataToReport(rv,ev) == 0) {
            NiceView niceView = new NiceView(
                    null,
                    ReportConverter.toModel(rv),
                    EmployeeConverter.toModel(ev),
                    AttributeConst.NICE_FLAG_FALSE.getIntegerValue(),
                    LocalDateTime.now());
            createInternal(NiceConverter.toModel(niceView));
        }
    }

    /**
     * いいねデータを作成する
     * @param rv いいねデータ
     */
    private void createInternal(Nice r) {
        em.getTransaction().begin();
        em.persist(r);
        em.getTransaction().commit();
    }

}
