package dbcontext.interfaces;

import dbcontext.models.User;

public interface IUserRepository {
    User getUser(int id);
}
