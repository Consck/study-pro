package sample.domain;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * @author bianjinyue
 * @Description 当更新时，ID不为空；新增时，ID必须是空
 * @date 2022-06-23 16:06
 */
@EntityScan
public class WorkInfoFormEntity {
    // 定义一个类，更新时校验组
    public interface Update{}

    // 定义另一个类，添加时校验组
    public interface Add{}

    /**
     * 当校验上下文为Add.class时，@NULL生效
     * 当校验上下文为Update.class时，@NotNULL生效
     */
    @NotNull(groups = {Update.class})
    @Null(groups = {Add.class})
    Long id;
}
