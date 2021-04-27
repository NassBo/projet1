package fr.nb.zyma.service.mapper;

import fr.nb.zyma.domain.*;
import fr.nb.zyma.service.dto.ApplicationUserDTO;
import java.util.Set;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ApplicationUser} and its DTO {@link ApplicationUserDTO}.
 */
@Mapper(componentModel = "spring", uses = { UserMapper.class })
public interface ApplicationUserMapper extends EntityMapper<ApplicationUserDTO, ApplicationUser> {
    @Mapping(target = "internalUser", source = "internalUser", qualifiedByName = "id")
    @Mapping(target = "friends", source = "friends", qualifiedByName = "idSet")
    ApplicationUserDTO toDto(ApplicationUser s);

    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ApplicationUserDTO toDtoId(ApplicationUser applicationUser);

    @Named("idSet")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    Set<ApplicationUserDTO> toDtoIdSet(Set<ApplicationUser> applicationUser);

    @Mapping(target = "removeFriends", ignore = true)
    ApplicationUser toEntity(ApplicationUserDTO applicationUserDTO);
}
