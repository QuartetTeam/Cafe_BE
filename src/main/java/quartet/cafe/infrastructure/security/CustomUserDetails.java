
package quartet.cafe.infrastructure.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {

    private final Integer userId;

    public CustomUserDetails(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override public String getPassword() { return null; }

    @Override public String getUsername() { return userId.toString(); }

    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}
