package ru.nsu.fit.directors.establishmentservice.service;

import ru.nsu.fit.directors.establishmentservice.dto.PhotoDto;
import ru.nsu.fit.directors.establishmentservice.model.Establishment;

import java.util.List;
import java.util.Set;

/**
 * Service, that responsible for saving images.
 */

public interface ImageService {

    /**
     * Saving images for provided establishment.
     *
     * @param photos        set of photos those we need to save.
     * @param establishment which will be associated with those photos.
     */
    void saveImages(Set<PhotoDto> photos, Establishment establishment);

    void deleteImages(List<String> images);
}
