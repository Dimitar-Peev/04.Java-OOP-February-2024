package magicGame.repositories;

import magicGame.models.magics.Magic;
import magicGame.repositories.interfaces.MagicRepository;

import java.util.*;

import static magicGame.common.ExceptionMessages.INVALID_MAGIC_REPOSITORY;

public class MagicRepositoryImpl implements MagicRepository<Magic> {
    private Map<String, Magic> data;

    public MagicRepositoryImpl() {
        this.data = new LinkedHashMap<>();
    }

    @Override
    public Collection<Magic> getData() {
        return Collections.unmodifiableCollection(this.data.values());
    }

    @Override
    public void addMagic(Magic magic) {
        if (magic == null) {
            throw new NullPointerException(INVALID_MAGIC_REPOSITORY);
        }
        this.data.put(magic.getName(), magic);
    }

    @Override
    public boolean removeMagic(Magic magic) {
        return this.data.remove(magic.getName()) != null;
    }

    @Override
    public Magic findByName(String name) {
        return this.data.get(name);
    }
}