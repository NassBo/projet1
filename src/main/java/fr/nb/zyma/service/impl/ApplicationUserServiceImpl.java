package fr.nb.zyma.service.impl;

import fr.nb.zyma.domain.ApplicationUser;
import fr.nb.zyma.repository.ApplicationUserRepository;
import fr.nb.zyma.service.ApplicationUserService;
import fr.nb.zyma.service.dto.ApplicationUserDTO;
import fr.nb.zyma.service.mapper.ApplicationUserMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ApplicationUser}.
 */
@Service
@Transactional
public class ApplicationUserServiceImpl implements ApplicationUserService {

    private final Logger log = LoggerFactory.getLogger(ApplicationUserServiceImpl.class);

    private final ApplicationUserRepository applicationUserRepository;

    private final ApplicationUserMapper applicationUserMapper;

    public ApplicationUserServiceImpl(ApplicationUserRepository applicationUserRepository, ApplicationUserMapper applicationUserMapper) {
        this.applicationUserRepository = applicationUserRepository;
        this.applicationUserMapper = applicationUserMapper;
    }

    @Override
    public ApplicationUserDTO save(ApplicationUserDTO applicationUserDTO) {
        log.debug("Request to save ApplicationUser : {}", applicationUserDTO);
        ApplicationUser applicationUser = applicationUserMapper.toEntity(applicationUserDTO);
        applicationUser = applicationUserRepository.save(applicationUser);
        return applicationUserMapper.toDto(applicationUser);
    }

    @Override
    public Optional<ApplicationUserDTO> partialUpdate(ApplicationUserDTO applicationUserDTO) {
        log.debug("Request to partially update ApplicationUser : {}", applicationUserDTO);

        return applicationUserRepository
            .findById(applicationUserDTO.getId())
            .map(
                existingApplicationUser -> {
                    applicationUserMapper.partialUpdate(existingApplicationUser, applicationUserDTO);
                    return existingApplicationUser;
                }
            )
            .map(applicationUserRepository::save)
            .map(applicationUserMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ApplicationUserDTO> findAll() {
        log.debug("Request to get all ApplicationUsers");
        return applicationUserRepository
            .findAllWithEagerRelationships()
            .stream()
            .map(applicationUserMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    public Page<ApplicationUserDTO> findAllWithEagerRelationships(Pageable pageable) {
        return applicationUserRepository.findAllWithEagerRelationships(pageable).map(applicationUserMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ApplicationUserDTO> findOne(Long id) {
        log.debug("Request to get ApplicationUser : {}", id);
        return applicationUserRepository.findOneWithEagerRelationships(id).map(applicationUserMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ApplicationUser : {}", id);
        applicationUserRepository.deleteById(id);
    }
}
