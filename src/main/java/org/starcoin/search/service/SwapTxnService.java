package org.starcoin.search.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.starcoin.search.bean.SwapPoolStat;
import org.starcoin.search.bean.SwapTransaction;
import org.starcoin.search.bean.TokenStat;
import org.starcoin.search.repository.SwapTransactionRepository;
import org.starcoin.search.repository.TokenVolumeDTO;

import java.util.List;

@Service
public class SwapTxnService {
    @Autowired
    private SwapTransactionRepository swapTransactionRepository;

    public void save(SwapTransaction swapTransaction) {
        swapTransactionRepository.save(swapTransaction);
    }

    public List<SwapTransaction> getAll() {
        return swapTransactionRepository.findAll();
    }

    public void saveList(List<SwapTransaction> swapTransactionList) {
        swapTransactionRepository.saveAllAndFlush(swapTransactionList);
    }

    public TokenStat getTokenVolume(String token, long startTime, long endTime) {
        TokenStat tokenStat = new TokenStat(token, startTime);
        TokenVolumeDTO tokenA = swapTransactionRepository.getVolumeByTokenA(token, startTime, endTime);
        TokenVolumeDTO tokenB = swapTransactionRepository.getVolumeByTokenA(token, startTime, endTime);
        tokenStat.setVolume(tokenA.getVolume().add(tokenB.getVolume()));
        tokenStat.setVolumeAmount(tokenA.getVolumeAmount().add(tokenB.getVolumeAmount()));
        return tokenStat;
    }

    public SwapPoolStat getPoolVolume(String tokenA, String tokenB, long startTime, long endTime) {
        SwapPoolStat poolStat = new SwapPoolStat(tokenA, tokenB, startTime);
        TokenVolumeDTO tokenADTO = swapTransactionRepository.getPoolVolumeA(tokenA, tokenB, startTime, endTime);
        TokenVolumeDTO tokenBDTO = swapTransactionRepository.getPoolVolumeB(tokenA, tokenB, startTime, endTime);
        poolStat.setVolume(tokenADTO.getVolume().add(tokenBDTO.getVolume()));
        poolStat.setVolumeAmount(tokenADTO.getVolumeAmount().add(tokenBDTO.getVolumeAmount()));
        return poolStat;
    }
}
