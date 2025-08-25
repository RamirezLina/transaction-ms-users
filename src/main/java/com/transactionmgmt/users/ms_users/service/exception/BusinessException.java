package com.transactionmgmt.users.ms_users.service.exception;

public class BusinessException extends RuntimeException {

    public enum Type{
        CLIENT_NOT_EXISTS("El cliente no existe");
        private final String message;
        
        public BusinessException build(){
            return new BusinessException(this);
        }

        Type(String message) {
            this.message = message;
        }
    }
    
    private final Type type;
    
    private BusinessException(Type type){
        super(type.message);
        this.type = type;
    }
}
