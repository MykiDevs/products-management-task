package exception;

import org.aspectj.weaver.ast.Not;

public abstract class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {
        super(message);
    }
}
