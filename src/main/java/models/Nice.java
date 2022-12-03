package models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import constants.JpaConst;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * いいねデータのDTOモデル
 *
 */
@Table(name = JpaConst.TABLE_NICE)
@NamedQueries({
    @NamedQuery(
            name = JpaConst.Q_NICE_ALL_NICE_COUNT_TO_REPORT,
            query = JpaConst.Q_NICE_ALL_NICE_COUNT_TO_REPORT_DEF),
    @NamedQuery(
            name = JpaConst.Q_NICE_COUNT_CREATED_MINE_NICE_DATA_TO_REPORT,
            query = JpaConst.Q_NICE_COUNT_CREATED_MINE_NICE_DATA_TO_REPORT_DEF),
    @NamedQuery(
            name = JpaConst.Q_NICE_COUNT_MINE_NICE_TO_REPORT,
            query = JpaConst.Q_NICE_COUNT_MINE_NICE_TO_REPORT_DEF),
    @NamedQuery(
            name = JpaConst.Q_NICE_GET_MINE_NICE_TO_REPORT,
            query = JpaConst.Q_NICE_GET_MINE_NICE_TO_REPORT_DEF)
})

@Getter //全てのクラスフィールドについてgetterを自動生成する(Lombok)
@Setter //全てのクラスフィールドについてsetterを自動生成する(Lombok)
@NoArgsConstructor //引数なしコンストラクタを自動生成する(Lombok)
@AllArgsConstructor //全てのクラスフィールドを引数にもつ引数ありコンストラクタを自動生成する(Lombok)
@Entity
public class Nice {

    /**
     * id
     */
    @Id
    @Column(name = JpaConst.NICE_COL_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * いいねをした従業員
     */
    @ManyToOne
    @JoinColumn(name = JpaConst.NICE_COL_EMP, nullable = false)
    private Employee employee;

    /**
     * いいねをした日報
     */
    @ManyToOne
    @JoinColumn(name = JpaConst.NICE_COL_REP, nullable = false)
    private Report report;

    /**
     * いいねの状況
     */
    @Column(name = JpaConst.NICE_COL_NICE_FLAG,nullable = false,columnDefinition = "integer default 0")
    private Integer niceFlag;

    /**
     * いいね日
     */
    @Column(name = JpaConst.NICE_COL_NICED_AT,nullable = false)
    private LocalDateTime nicedAt;

}
