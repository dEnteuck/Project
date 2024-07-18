package vlu.android.project.Model;

public class Match {
    private String teamLogo1;
    private String teamLogo2;
    private String teamName1;
    private String teamName2;
    private String teamScore1;
    private String teamScore2;
    private String matchStatus;
    private String shooting1;
    private String shooting2;
    private String attacks1;
    private String attacks2;
    private String possession1;
    private String possession2;
    private String cards1;
    private String cards2;
    private String corners1;
    private String corners2;

    public Match(String teamLogo1, String teamLogo2, String teamName1, String teamName2, String teamScore1, String teamScore2, String matchStatus) {
        this.teamLogo1 = teamLogo1;
        this.teamLogo2 = teamLogo2;
        this.teamName1 = teamName1;
        this.teamName2 = teamName2;
        this.teamScore1 = teamScore1;
        this.teamScore2 = teamScore2;
        this.matchStatus = matchStatus;
    }

    public Match() {
    }

    public String getTeamLogo1() {
        return teamLogo1;
    }

    public void setTeamLogo1(String teamLogo1) {
        this.teamLogo1 = teamLogo1;
    }

    public String getTeamLogo2() {
        return teamLogo2;
    }

    public void setTeamLogo2(String teamLogo2) {
        this.teamLogo2 = teamLogo2;
    }

    public String getTeamName1() {
        return teamName1;
    }

    public void setTeamName1(String teamName1) {
        this.teamName1 = teamName1;
    }

    public String getTeamName2() {
        return teamName2;
    }

    public void setTeamName2(String teamName2) {
        this.teamName2 = teamName2;
    }

    public String getTeamScore1() {
        return teamScore1;
    }

    public void setTeamScore1(String teamScore1) {
        this.teamScore1 = teamScore1;
    }

    public String getTeamScore2() {
        return teamScore2;
    }

    public void setTeamScore2(String teamScore2) {
        this.teamScore2 = teamScore2;
    }

    public String getMatchStatus() {
        return matchStatus;
    }

    public void setMatchStatus(String matchStatus) {
        this.matchStatus = matchStatus;
    }

    public String getShooting1() {
        return shooting1;
    }

    public void setShooting1(String shooting1) {
        this.shooting1 = shooting1;
    }

    public String getShooting2() {
        return shooting2;
    }

    public void setShooting2(String shooting2) {
        this.shooting2 = shooting2;
    }

    public String getAttacks1() {
        return attacks1;
    }

    public void setAttacks1(String attacks1) {
        this.attacks1 = attacks1;
    }

    public String getAttacks2() {
        return attacks2;
    }

    public void setAttacks2(String attacks2) {
        this.attacks2 = attacks2;
    }

    public String getPossession1() {
        return possession1;
    }

    public void setPossession1(String possession1) {
        this.possession1 = possession1;
    }

    public String getPossession2() {
        return possession2;
    }

    public void setPossession2(String possession2) {
        this.possession2 = possession2;
    }

    public String getCards1() {
        return cards1;
    }

    public void setCards1(String cards1) {
        this.cards1 = cards1;
    }

    public String getCards2() {
        return cards2;
    }

    public void setCards2(String cards2) {
        this.cards2 = cards2;
    }

    public String getCorners1() {
        return corners1;
    }

    public void setCorners1(String corners1) {
        this.corners1 = corners1;
    }

    public String getCorners2() {
        return corners2;
    }

    public void setCorners2(String corners2) {
        this.corners2 = corners2;
    }


}
