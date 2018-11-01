package com.wiceflow.entity.vo;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author BF
 * @date 2018/10/29
 */
public class ParamList<E> {

    @Valid
    @NotEmpty(message = "数据列表为空")
        private List<E> list;

    public ParamList() {
    }


    public List<E> getList() {
        return list;
    }

    public void setList(List<E> list) {
        this.list = list;
    }
}
