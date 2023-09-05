package guru.qa.utils;

public enum TypeExt {
    CSV(".csv"),
    PDF(".pdf"),
    TXT(",txt"),
    XLSX(".xlsx");



    private final String fileExt;
    private TypeExt(String ext) {
        this.fileExt = ext;
    }

    public String getfileExt() {
        return this.fileExt;
    }

}
