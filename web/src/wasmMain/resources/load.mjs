import { instantiate } from './web.uninstantiated.mjs';

await wasmSetup;

try {
    await instantiate({ skia: Module['asm'] });
} catch (e) {
   throw e;
}