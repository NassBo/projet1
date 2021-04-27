package fr.nb.zyma.service.mapper;

import fr.nb.zyma.domain.*;
import fr.nb.zyma.service.dto.ArticleDTO;
import java.util.Set;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Article} and its DTO {@link ArticleDTO}.
 */
@Mapper(componentModel = "spring", uses = { TagMapper.class, ApplicationUserMapper.class })
public interface ArticleMapper extends EntityMapper<ArticleDTO, Article> {
    @Mapping(target = "tags", source = "tags", qualifiedByName = "idSet")
    @Mapping(target = "applicationUser", source = "applicationUser", qualifiedByName = "id")
    ArticleDTO toDto(Article s);

    @Mapping(target = "removeTags", ignore = true)
    Article toEntity(ArticleDTO articleDTO);
}
