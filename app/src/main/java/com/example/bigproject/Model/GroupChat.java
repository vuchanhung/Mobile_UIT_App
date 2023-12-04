import java.util.List;

public class GroupChat {
        private String groupId;
        private String groupName;
        private List<String> members;

        // Constructors, getters, setters, etc.

        // Default constructor required for Firebase
        public GroupChat() {
        }

        public GroupChat(String groupId, String groupName, List<String> members) {
                this.groupId = groupId;
                this.groupName = groupName;
                this.members = members;
        }

        public String getGroupId() {
                return groupId;
        }

        public void setGroupId(String groupId) {
                this.groupId = groupId;
        }

        public String getGroupName() {
                return groupName;
        }

        public void setGroupName(String groupName) {
                this.groupName = groupName;
        }

        public List<String> getMembers() {
                return members;
        }

        public void setMembers(List<String> members) {
                this.members = members;
        }
}
