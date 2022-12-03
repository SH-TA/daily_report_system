package actions.views;

import constants.AttributeConst;
import constants.JpaConst;
import models.Nice;

/**
 * いいねデータのDTOモデル⇔Viewモデルの変換を行うクラス
 *
 */
public class NiceConverter {

    /**
     * ViewモデルのインスタンスからDTOモデルのインスタンスを作成する
     * @param rv NiceViewのインスタンス
     * @return Niceのインスタンス
     */
    public static Nice toModel(NiceView rv) {
        return new Nice(
                rv.getId(),
                rv.getEmployee(),
                rv.getReport(),
                rv.getNiceFlag() == null
                    ? null
                    : rv.getNiceFlag() == AttributeConst.NICE_FLAG_TRUE.getIntegerValue()
                        ? JpaConst.NICE_REP_TRUE
                        : JpaConst.NICE_REP_FALSE,
                rv.getNicedAt());
    }

    /**
     * DTOモデルのインスタンスからViewモデルのインスタンスを作成する
     * @param r Niceのインスタンス
     * @return NiceViewのインスタンス
     */
    public static NiceView toView(Nice r) {
        return new NiceView(
                r.getId(),
                r.getReport(),
                r.getEmployee(),
                r.getNiceFlag() == null
                    ? null
                    : r.getNiceFlag() == JpaConst.NICE_REP_TRUE
                        ? AttributeConst.NICE_FLAG_TRUE.getIntegerValue()
                        : AttributeConst.NICE_FLAG_FALSE.getIntegerValue(),
                r.getNicedAt());
    }

    /**
     * Viewモデルの全フィールドの内容をDTOモデルのフィールドにコピーする
     * @param r DTOモデル(コピー先)
     * @param rv Viewモデル(コピー元)
     */
    public static void copyViewToModel(Nice r,NiceView rv) {
        r.setId(rv.getId());
        r.setReport(rv.getReport());
        r.setEmployee(rv.getEmployee());
        r.setNiceFlag(rv.getNiceFlag());
        r.setNicedAt(rv.getNicedAt());
    }

}
