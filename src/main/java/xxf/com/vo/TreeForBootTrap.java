package xxf.com.vo;

import java.util.List;



public class TreeForBootTrap {
    /**
     * text	String(必选项)	列表树节点上的文本，通常是节点右边的小图标。
     * icon	String(可选项)	列表树节点上的图标，通常是节点左边的图标。
     * selectedIcon	String(可选项)	当某个节点被选择后显示的图标，通常是节点左边的图标。
     * href	String(可选项)	结合全局enableLinks选项为列表树节点指定URL。
     * selectable	Boolean. Default: true	指定列表树的节点是否可选择。设置为false将使节点展开，并且不能被选择。
     * state	Object(可选项)	一个节点的初始状态。
     * state.checked	Boolean，默认值false	指示一个节点是否处于checked状态，用一个checkbox图标表示。
     * state.disabled	Boolean，默认值false	指示一个节点是否处于disabled状态。（不是selectable，expandable或checkable）
     * state.expanded	Boolean，默认值false	指示一个节点是否处于展开状态。
     * state.selected	Boolean，默认值false	指示一个节点是否可以被选择。
     * color	String. Optional	节点的前景色，覆盖全局的前景色选项。
     * backColor	String. Optional	节点的背景色，覆盖全局的背景色选项。
     * tags
     */
    private String id;
    private String fid;
    private String text;
    private String icon;
    private String selectedIcon;
    private String href;
    private String selectable;
    private String color;
    private String backColor;
    private List<TreeForBootTrap> nodes;
    private Stat stat;

    public Stat getStat() {
        return stat;
    }

    public void setStat(Stat stat) {
        this.stat = stat;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public List<TreeForBootTrap> getNodes() {
        return nodes;
    }

    public void setNodes(List<TreeForBootTrap> nodes) {
        this.nodes = nodes;
    }

    @Override

    public String toString() {
        return "TreeForBootTrap{" +
                "text='" + text + '\'' +
                ", icon='" + icon + '\'' +
                ", selectedIcon='" + selectedIcon + '\'' +
                ", href='" + href + '\'' +
                ", selectable='" + selectable + '\'' +
                ", color='" + color + '\'' +
                ", backColor='" + backColor + '\'' +
                '}';
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getSelectedIcon() {
        return selectedIcon;
    }

    public void setSelectedIcon(String selectedIcon) {
        this.selectedIcon = selectedIcon;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getSelectable() {
        return selectable;
    }

    public void setSelectable(String selectable) {
        this.selectable = selectable;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBackColor() {
        return backColor;
    }

    public void setBackColor(String backColor) {
        this.backColor = backColor;
    }
}


