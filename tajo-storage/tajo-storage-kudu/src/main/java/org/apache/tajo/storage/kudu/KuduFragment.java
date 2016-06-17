package org.apache.tajo.storage.kudu;

import org.apache.tajo.storage.fragment.Fragment;

import org.apache.tajo.util.Bytes;
import org.kududb.client.KuduClient;

import java.net.URI;

/**
 * Fragment for Kudu
 */
public class KuduFragment extends Fragment<KuduFragment.KuduFragmentKey> {


    protected KuduFragment(String kind, URI uri, String inputSourceId, byte[] startKey, byte[] endKey, long length, String[] hostNames) {
        super(kind, uri, inputSourceId, new KuduFragmentKey(startKey), new KuduFragmentKey(endKey), length, hostNames);
    }


    @Override
    public boolean isEmpty() {
        return startKey.isEmpty() || endKey.isEmpty();
    }

    @Override
    public String toString() {
        return "";
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public static class KuduFragmentKey implements Comparable<KuduFragmentKey> {
        private final byte[] bytes;

        public KuduFragmentKey(byte[] key) {
            this.bytes = key;
        }

        public byte[] getBytes() {
            return bytes;
        }

        @Override
        public int hashCode() {
            return Bytes.hashCode(bytes);
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof KuduFragmentKey) {
                KuduFragmentKey other = (KuduFragmentKey) o;
                return Bytes.equals(bytes, other.bytes);
            }
            return false;
        }

        @Override
        public int compareTo(KuduFragmentKey o) {
            return Bytes.compareTo(bytes, o.bytes);
        }

        @Override
        public String toString() {
            return new String(bytes);
        }

        public boolean isEmpty() {
            return bytes == null;
        }
    }
}
