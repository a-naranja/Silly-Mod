package data.scripts;

import com.fs.starfarer.api.impl.PlanetSearchData;

public class sillyPlanetSearchData extends PlanetSearchData {
    static {
        ResourceDepositsData sillyParticles = new ResourceDepositsData("silly_particles", "Silly Particles");
        sillyParticles.conditions.add("silly_particles_sparse");
        sillyParticles.conditions.add("silly_particles_moderate");
        sillyParticles.conditions.add("silly_particles_abundant");

        RESOURCE_DEPOSITS.add(sillyParticles);
    }
}
