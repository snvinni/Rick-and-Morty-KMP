import { instantiate } from './webAppModule.uninstantiated.mjs';

await wasmSetup;

try {
    await instantiate({ skia: Module['asm'] });
} catch (e) {
   throw e;
}