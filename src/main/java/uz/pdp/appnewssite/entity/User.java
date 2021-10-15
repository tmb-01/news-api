package uz.pdp.appnewssite.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.pdp.appnewssite.entity.enums.RoleNames;
import uz.pdp.appnewssite.entity.template.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "users")
public class User extends AbstractEntity implements UserDetails {
    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Role roles;


    private boolean enabled;

    private boolean accountNonExpired = true;

    private boolean credentialsNonExpired = true;
    private boolean isAccountNonLocked = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<RoleNames> roleNames = this.roles.getRoleNames();
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (RoleNames roleName : roleNames) {
            grantedAuthorities.add(new SimpleGrantedAuthority(roleName.name()));
        }
        return grantedAuthorities;
    }
}
