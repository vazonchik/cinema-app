package cinema.dao.impl;

import cinema.dao.AbstractDao;
import cinema.dao.RoleDao;
import cinema.exception.DataProcessingException;
import cinema.model.Role;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl extends AbstractDao<Role> implements RoleDao {
    public RoleDaoImpl(SessionFactory factory) {
        super(factory, Role.class);
    }

    @Override
    public Optional<Role> getByName(Role.RoleEnum role) {
        String query = "FROM Role r WHERE r.roleName = :role";
        try (Session session = factory.openSession()) {
            return session.createQuery(query, Role.class)
                    .setParameter("role", role)
                    .uniqueResultOptional();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get Role with name " + role, e);
        }
    }
}
