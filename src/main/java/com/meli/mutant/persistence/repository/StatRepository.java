package com.meli.mutant.persistence.repository;

import com.meli.mutant.domain.model.StatModel;
import com.meli.mutant.domain.repository.StatDomainRepository;
import com.meli.mutant.domain.service.DnaSequenceService;
import com.meli.mutant.persistence.mongo.StatMongoRepository;
import com.meli.mutant.persistence.entity.Stat;
import com.meli.mutant.persistence.mapper.StatMapper;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.stereotype.Repository;

@Repository
public class StatRepository implements StatDomainRepository {
    private final Log LOGGER = LogFactory.getLog(DnaSequenceService.class);
    private final StatMongoRepository statMongoRepository;
    private final StatMapper mapper;

    public StatRepository(StatMongoRepository statMongoRepository, StatMapper statMapper) {
        this.statMongoRepository = statMongoRepository;
        this.mapper = statMapper;
    }

    @Override
    public StatModel getStat() {
        Stat stat = statMongoRepository.findAll().stream().findFirst().orElse(null);
        return mapper.toStatModel(stat);
    }

    @Override
    public void updateStats(StatModel newStatModel) {
        LOGGER.debug("StatRepository method updateStats" + newStatModel);

        if (newStatModel.getIdStat() == null) {
            createStat(newStatModel);
        } else {
            Stat stat = statMongoRepository.findById(newStatModel.getIdStat()).orElse(null);
            if (stat != null) {
                stat.setCount_mutant_dna(newStatModel.getCountMutantDna());
                stat.setCount_human_dna(newStatModel.getCountHumanDna());
                stat.setRatio(newStatModel.getRatioStat());
                statMongoRepository.save(stat);
            }
        }
    }

    @Override
    public void createStat(StatModel statModel) {
        LOGGER.debug("StatRepository method createStat" + statModel);
        Stat stat = mapper.toStat(statModel);
        mapper.toStatModel(statMongoRepository.save(stat));
    }
}
