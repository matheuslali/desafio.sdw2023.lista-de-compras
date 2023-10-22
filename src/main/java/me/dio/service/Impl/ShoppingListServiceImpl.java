package me.dio.service.Impl;

import me.dio.domain.model.ShoppingList;
import me.dio.domain.model.User;
import me.dio.domain.repository.ShoppingListRepository;
import me.dio.service.ShoppingListService;
import me.dio.service.exception.BusinessException;
import me.dio.service.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingListServiceImpl implements ShoppingListService {

    private final ShoppingListRepository shoppingListRepository;

    public ShoppingListServiceImpl(ShoppingListRepository shoppingListRepository){
        this.shoppingListRepository = shoppingListRepository;
    }

    @Override
    public List<ShoppingList> findAll() {
        return this.shoppingListRepository.findAll();
    }

    @Override
    public ShoppingList findById(Long id)  {

        return this.shoppingListRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public ShoppingList create(ShoppingList shoppingListCreate) {
        if(shoppingListCreate.getId().equals(shoppingListRepository.findById(shoppingListCreate.getId()))){
            throw new BusinessException("This id number already exists.");
        }
        return this.shoppingListRepository.save(shoppingListCreate);
    }

    @Override
    public ShoppingList update(Long id, ShoppingList shoppingListUpdate) {
        ShoppingList dbList = this.findById(id);
        if(!dbList.getId().equals(shoppingListUpdate.getId())){
            throw new BusinessException("Update IDs must be the same.");
        }

        dbList.setProductName(shoppingListUpdate.getProductName());
        dbList.setQuantity(shoppingListUpdate.getQuantity());

        return this.shoppingListRepository.save(dbList);
    }

    @Override
    public void delete(Long id) {
        ShoppingList dbList = findById(id);
        this.shoppingListRepository.delete(dbList);
    }
}
