package org.code.challenge.model.exception;

public class DataExtractionException extends Exception{

    private Object id;

    private Class typeOfFile;

   public DataExtractionException(Object id, Class typeOfFile){
       this.id = id;
       this.typeOfFile = typeOfFile;
   }

    @Override
    public String getMessage() {
        return new StringBuilder("Couldn't retrive data of type: " ).append(typeOfFile.getName()).append(" with id: ").append(id.toString()).toString();
    }
}
