public class DocumentMetaData {
	public long bytes;
	private String fileName;
	private String extension;	// e.g. "pdf"
	static private FileType fileType;

	public DocumentMetaData(){}
	
	public DocumentMetaData(String fileName){
		this.fileName = fileName;
		extension = fileName.substring(fileName.indexOf('.'), fileName.length());	
		setFileType(fileName);
	}

	public FileType getFileType(){
		return fileType;
	}
	
	private void setFileType(String fileName){		
		fileType = FileType.getFileType(fileName.substring(fileName.indexOf('.')+1, fileName.length())); 
	}
	
	public long getBytes(){ 
		return bytes; 
	}
	
	public void setBytes(long bytes){ 
		this.bytes = bytes; 
	}
			
	public String getFileName(){ 
		return fileType.extension; 
	}
	
// TODO - do we need to let users of this class set the fileName?
//	public void setFileName(String fileName){ 
//		this.fileName = fileName; 
//	}
	
	private enum FileType{
		PDF("pdf", true),
		XLSX("xlsx", true),
		JPG("jpg", true),
		DOCX("docx", true),
		ZIP("zip", false),
		UNKNOWN(null, false);
		
		private String extension;
		private boolean isPrintable;
		
		private FileType(String extension, boolean isPrintable){
			this.extension = extension;
			this.isPrintable = isPrintable;
		}
				
		public static FileType getFileType(String extension){
			switch(extension){
				case "pdf":
					return PDF;
				case "xlsx":
					return XLSX;
				case "docx":
					return DOCX;
				case "zip":
					return ZIP;
				default:
					return UNKNOWN;
			}
		}
	}
}