package menzonev2.example.demo.services.impl;

import menzonev2.example.demo.services.HashingService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

@Service
 class HashingServiceImpl implements HashingService {



        @Override
        public String hash(String str) {
            return DigestUtils.sha256Hex(str);
        }

}
