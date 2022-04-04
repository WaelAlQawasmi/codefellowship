package com.example.CodeFellowship.table;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Setter// use to create setter for all attribute
@Getter// use to create getter for all attribute
@NoArgsConstructor // to create deflate constructor ( constactor with out any args)
@RequiredArgsConstructor// to create constactor with arg that have  @NonNull
@Entity // to crate table for this class
public class ApplicationUser implements UserDetails {

   @Setter(value = AccessLevel.NONE)
   @Id
   @GeneratedValue
//   @Column(name = "id", nullable = false)
   private Long id;

   @NonNull
   @Column(unique = true)
   private String username;
   @NonNull
   private String password ;
   @NonNull
   private String firstName;
   @NonNull
   private String lastName;
   @NonNull
   private String dateOfBirth;
   @NonNull
   private String bio;




   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      return null;
   }

   @Override
   public boolean isAccountNonExpired() {
      return true;
   }

   @Override
   public boolean isAccountNonLocked() {
      return true;
   }

   @Override
   public boolean isCredentialsNonExpired() {
      return true;
   }

   @Override
   public boolean isEnabled() {
      return true;
   }


   @OneToMany(mappedBy="applicationUser")
   List<post> posts;
}
