package fuwu.em;

public enum InteractionTypeEnum {
    BASE_INTERACTION(0,"基本类型"),
    COMPLEXY_INTERACTION(1,"组合类型"),
    ;

    private Integer interactiontype;
    private String interactionDesc;

    InteractionTypeEnum(Integer interactiontype, String interactionDesc) {
        this.interactiontype = interactiontype;
        this.interactionDesc = interactionDesc;
    }

    public Integer getInteractiontype() {
        return interactiontype;
    }

    public String getInteractionDesc() {
        return interactionDesc;
    }
}
