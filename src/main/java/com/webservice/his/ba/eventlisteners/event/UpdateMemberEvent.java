package com.webservice.his.ba.eventlisteners.event;

public class UpdateMemberEvent extends Event {
    private static final long serialVersionUID = -5004482826577590328L;
    private String oldMember;

    public String getOldMember() {
        return oldMember;
    }

    public void setOldMember(String oldMember) {
        this.oldMember = oldMember;
    }

    public UpdateMemberEvent(Object source) {
        super(source);
    }
}