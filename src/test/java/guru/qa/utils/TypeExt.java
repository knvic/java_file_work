package guru.qa.utils;

public enum TypeExt {
    CSV(".csv" ),
    PDF(".pdf" ),
    TXT(",txt" ),
    XLSX(".xlsx");

private final String fileExtension;
    TypeExt( String ext) {
        this.fileExtension = ext;
    }
    public String getfileExtension() {
        return fileExtension;
    }

}
