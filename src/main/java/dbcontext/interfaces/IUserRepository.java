package dbcontext.interfaces;

import dbcontext.models.MarioUser;

public interface IUserRepository {
    MarioUser getUser(int id);
    MarioUser getUser(String name, String password);
    boolean hasUser(String name);
}
